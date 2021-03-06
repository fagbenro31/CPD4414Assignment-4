/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
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
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 * Provides an Account Balance and Basic Withdrawal/Deposit Operations
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    Account acc = new Account();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "private, no-store, nocache,must - revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(String.valueOf(acc.getBalance()));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "you broke something");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String withdraw = request.getParameter("withdraw");
        String deposit = request.getParameter("deposit");
        String close = request.getParameter("close");

        System.out.println(withdraw);
        System.out.println(deposit);
        System.out.println(close);
        if (withdraw != null) {
            acc.withdraw(Double.parseDouble(withdraw));
        } else if (deposit != null) {
            acc.deposit(Double.parseDouble(deposit));
        } else if (close != null) {
            acc.close();
        } else {
            request.setAttribute("error", "you broke something");

        }
    }
}
