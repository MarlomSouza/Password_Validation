/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package password;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import helpers.MensagensValidacoes;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private List<String> mensagensEsperada;

    @Test
    public void noParameterShouldReturnMessageNoPasswordInformed() throws Exception {
        this.mockMvc.perform(get ("/api/passwordvalidation"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.quantidadeMinimaCaracteres));
    }
    @Test
    public void shouldReturnPasswordInformed() throws Exception {
       
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "senha.123"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.senha").value("senha.123"));
    }

    @Test
    public void noUpperCaseLetter() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "s1e2n3h."))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.quantidadeMinimaCaracterMaisculo));
    }

    @Test
    public void noLowerCaseLetters() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "S1E2N3H."))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.quantidadeMinimaCaracterMinusculo));
    }

    
    @Test
    public void noNumberLetters() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "SeNaPiUhK."))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.quantidadeMinimaCaracterNumerico));
    }

    @Test
    public void noSpecialLetters() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "SeNaPiUhK3"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.quantidadeMinimaCaracterEspecial));
    }

    @Test
    public void NoNumberAndSymbols() throws Exception {
        mensagensEsperada = new ArrayList<String>();
        mensagensEsperada.add(MensagensValidacoes.quantidadeMinimaCaracterEspecial);    
        mensagensEsperada.add(MensagensValidacoes.quantidadeMinimaCaracterNumerico);
            

        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "SeNhAbDj"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(mensagensEsperada));
    }

    @Test
    public void caracterSequence() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "AbCdXf1."))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(MensagensValidacoes.ExisteCaracterSequencial));
    }

    @Test
    public void caracterNumericSequence() throws Exception {
        mensagensEsperada = new ArrayList<String>();
        mensagensEsperada.add(MensagensValidacoes.ExisteCaracterNumericoSequencial);    
        mensagensEsperada.add(MensagensValidacoes.ExisteCaracterNumericosConsecutivos);
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "AgCdXf123."))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value(mensagensEsperada));
    }









    // @Test
    // public void noLowerCaseLetter() throws Exception {
    //     this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "SENHA.123"))
    //                 .andDo(print()).andExpect(status().isOk())
    //                 .andExpect(jsonPath("$.mensagens").value("eh necessario possuir uma letra minuscula."));
    // }

    // @Test
    // public void noLowerCaseLetter() throws Exception {
    //     this.mockMvc.perform(.)
    //                 .andDo(print()).andExpect(status().isOk())
    //                 .andExpect(jsonPath("$.mensagens").value("eh necessario possuir uma letra minuscula."));
    // }

    // @Test
    // public void paramGreetingShouldReturnTailoredMessage() throws Exception {

    //     this.mockMvc.perform(get("/").param("name", "Spring Community"))
    //             .andDo(print()).andExpect(status().isOk())
    //             .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    // }

}
