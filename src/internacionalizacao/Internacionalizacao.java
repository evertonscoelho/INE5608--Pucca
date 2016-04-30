package internacionalizacao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Internacionalizacao {

	HashMap<String, String> translate = new HashMap<String, String>();

	public Internacionalizacao(String locale) {
		try {
			loadTranslate(locale);
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
	}

	private void loadTranslate(String locale) throws IOException {
		String nameFile = "Traducoes/" + locale + ".properties";
		try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
			String line;
			String[] value;
			while ((line = br.readLine()) != null) {
				value = line.split("=");
				translate.put(value[0].trim(), value[1].trim());
			}
		}
	}

	public String getTranslate(String key) throws Exception {
		String value = translate.get(key);
		if (value != null) {
			return value;
		} else {
			throw new Exception("Palavra não encontrada");
		}
	}

	public String getKeyForValue(String value) {
		for(String key: translate.keySet()){
			if(translate.get(key).equals(value)){
				return key;
			}
		}
		return null;
	}
}
