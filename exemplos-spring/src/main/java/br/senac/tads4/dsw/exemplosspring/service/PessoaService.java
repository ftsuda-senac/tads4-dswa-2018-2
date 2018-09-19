/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;


import br.senac.tads4.dsw.exemplosspring.model.Pessoa;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
public class PessoaService {

    private static final Map<Long, Pessoa> PESSOAS = new ConcurrentHashMap<Long, Pessoa>();
    
    private static long SEQUENCIA;

    static {
        SEQUENCIA = 1L;
        URL url;
        try {
            url = new URL("https://randomuser.me/api/?seed=tads&page=1&results=32&nat=br&inc=name,gender,cell,email,picture,dob");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            Map<String, List<String>> map = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Tratamento do JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.toString());
            JsonNode results = root.get("results");
            Iterator it = results.elements();
            while (it.hasNext()) {
                JsonNode person = (JsonNode) it.next();
                Pessoa p = new Pessoa();
                p.setId(SEQUENCIA);
                SEQUENCIA++;
                p.setNome(new String(person.at("/name/first").asText().getBytes("ISO-8859-1"), "UTF-8") + " "
                        + new String(person.at("/name/last").asText().getBytes("ISO-8859-1"), "UTF-8"));
                p.setDtNascimento(LocalDate.parse(person.at("/dob/date").asText().subSequence(0, 10)));
                p.setEmail(new String(person.at("/email").asText().getBytes("ISO-8859-1"), "UTF-8"));
                p.setTelefone(person.at("/cell").asText());
                p.setUrlImagem(person.at("/picture/large").asText());
                String gender = person.at("/gender").asText();
                if ("male".equals(gender)) {
                    p.setSexo(1);
                } else {
                    p.setSexo(0);
                }
                PESSOAS.put(p.getId(), p);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(PessoaService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PessoaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pessoa> listar() {
        return new ArrayList<Pessoa>(PESSOAS.values());
    }

    public Pessoa obter(Long id) {
        return PESSOAS.get(id);
    }

    public static void main(String[] args) {
        for (Pessoa p : PESSOAS.values()) {
            System.out.println(p.toString());
        }
    }

    public synchronized void salvar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            pessoa.setId(SEQUENCIA);
            SEQUENCIA++;
            PESSOAS.put(pessoa.getId(), pessoa);
        } else {
            if (PESSOAS.containsKey(pessoa.getId())) {
                PESSOAS.put(pessoa.getId(), pessoa);
            }
        }
    }

}
