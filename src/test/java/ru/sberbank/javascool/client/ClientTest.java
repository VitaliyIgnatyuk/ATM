package ru.sberbank.javascool.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ClientTest {

    Person person = mock(Person.class);

    Client client = Client.createClient(person);

    @Test
    public void getPerson() {
        Assert.assertSame(client.getPerson(), person);
    }

    @Test
    public void getNumber() {
        Assert.assertEquals(client.getNumber(), 1);
    }
}