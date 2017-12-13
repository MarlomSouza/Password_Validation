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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParameterShouldReturnMessageNoPasswordInformed() throws Exception {
        this.mockMvc.perform(get ("/api/passwordvalidation"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value("Quantidade minima de 8 caracteres"));
    }
    @Test
    public void shouldReturnPasswordInformed() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "senha.123"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.senha").value("senha.123"));
    }

    @Test
    public void noUpperCaseLetter() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "senha.123"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value("eh necessario possuir uma letra maiuscula."));
    }

    @Test
    public void noLowerCaseLetter() throws Exception {
        this.mockMvc.perform(get("/api/passwordvalidation").param("senha", "SENHA.123"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensagens").value("eh necessario possuir uma letra minuscula."));
    }

    // @Test
    // public void paramGreetingShouldReturnTailoredMessage() throws Exception {

    //     this.mockMvc.perform(get("/").param("name", "Spring Community"))
    //             .andDo(print()).andExpect(status().isOk())
    //             .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    // }

}
