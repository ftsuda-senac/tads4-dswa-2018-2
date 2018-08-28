/*
 * The MIT License
 *
 * Copyright 2018 Senac TADS DSW-B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.senac.tads4.dswa.exemplos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoServiceFakeImpl implements ProdutoService {

    private Map<Long,Produto> produtos = new ConcurrentHashMap<Long,Produto>();

    public ProdutoServiceFakeImpl() {
        for (long i = 1; i < 20; i++) {
            Produto p = new Produto(i, "Produto " + i, "Descrição do produto", LocalDateTime.now(), new BigDecimal(i * 20 % 100), new BigDecimal(i * 30 % 120), 100, "http://via.placeholder.com/300x200");
            produtos.put(i, p);
        }
    }

    @Override
    public List<Produto> listar(int offset, int quantidade) {
        return new ArrayList<>(produtos.values());
    }

}
