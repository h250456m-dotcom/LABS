/** Lab 1, Exercise 5 (Challenge): a Person HAS an Address; chained dot access and null. */
public class Lab01Ex5 {

    static class Address {
        String street;
        String city;
    }

    static class Person {
        String name;
        Address address;   // may be null
    }

    public static void main(String[] args) {
        Address a = new Address();
        a.street = "12 Samora Machel Ave";
        a.city = "Harare";

        Person withAddress = new Person();
        withAddress.name = "Tendai";
        withAddress.address = a;

        // chained dot expression: person -> address -> city
        System.out.println(withAddress.name + " lives in " + withAddress.address.city);

        Person noAddress = new Person();
        noAddress.name = "Chipo";   // address was never assigned, so it is null

        // Unsafe: dereferencing null throws NullPointerException
        try {
            System.out.println(noAddress.address.city);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + noAddress.name + " has no address object");
        }

        // Safe: test for null first
        if (noAddress.address != null) {
            System.out.println(noAddress.address.city);
        } else {
            System.out.println(noAddress.name + ": address is null, city unknown");
        }
    }
}
