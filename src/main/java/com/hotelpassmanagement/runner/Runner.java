package com.hotelpassmanagement.runner;

class Runner {
    public static void main(String args[]) {
        OutPass obj = new OutPass();
        obj.createpass("Nishkarsh", "D-203", "B-Tech: Computer Science", "sap",9005445752l, 9415956617l);
        obj.permission();
    }
}