
package com.example.unitestapplication.view.tests_creation;

import java.net.InterfaceAddress;

public interface tests_creationView {
    int getquantity();

    String getlevel();

    int getid();

    void fixlevel();

    void setlevel(String level);

    void setQuantity(int q);

    void showsuccessCreateTest(String s, String s1);

    void showErrorMessage(String s, String missingFields);
}
