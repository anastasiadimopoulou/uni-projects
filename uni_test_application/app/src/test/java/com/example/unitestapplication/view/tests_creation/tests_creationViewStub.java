package com.example.unitestapplication.view.tests_creation;

public class tests_creationViewStub implements tests_creationView {
    private int quantity;
    private String level;
    private String successTitle;
    private String successMessage;
    private String errorTitle;
    private String errorMessage;

    // Simulate getting the quantity from the user
    @Override
    public int getquantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Simulate getting the level from the user
    @Override
    public String getlevel() {
        return level;
    }

    @Override
    public int getid() {
        return 0;
    }

    @Override
    public void fixlevel() {

    }



    @Override
    public void setlevel(String level) {
        this.level = level;
    }

    // Simulate displaying a success message
    @Override
    public void showsuccessCreateTest(String title, String message) {
        this.successTitle = title;
        this.successMessage = message;
    }

    public String getSuccessTitle() {
        return successTitle;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    // Simulate displaying an error message
    @Override
    public void showErrorMessage(String title, String message) {
        this.errorTitle = title;
        this.errorMessage = message;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
