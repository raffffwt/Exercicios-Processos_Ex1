package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();

	}

	private String os() {
		String os = System.getProperty("os.name");
		return "os-name " + os;
	}

	public String ip() {
		String osName = this.os();
		String process = "";
		String adapt = "";
		if (osName.contains("Windows")) {
			process = "IPCONFIG";
		} else if (osName.contains("Linux")) {
			process = "IFCONFIG";
		}
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("Adaptador")) {
					adapt = linha;
					linha = buffer.readLine();
					continue;
				}
				if (linha.contains("IPv4")) {
					System.out.println(adapt);
					System.out.println(linha);
					linha = buffer.readLine();
				} else {
					linha = buffer.readLine();
					continue;
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public double ping() {
		String process = "";
		String osName = this.os();
		float media = 0;
		int latency = 0;
		if (osName.contains("Windows")) {
			process = "PING -4 -n 10 www.google.com.br";
		} else if (osName.contains("Linux")) {
			process = "PING -4 -c 10 www.google.com.br";
		}
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) {
				linha = buffer.readLine();
				if (linha.contains("tempo")) {
					String timeWithValue = linha.split(" ")[4];
					String value = timeWithValue.split("=")[1];
					value = value.replace("ms", "");

					latency += Integer.parseInt(value);
				} else {
					buffer.readLine();
					break;
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		media = latency / 10;
		System.out.println("Tempo médio: " + media);
		return media;
	}
}
