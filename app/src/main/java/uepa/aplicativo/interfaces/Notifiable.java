package uepa.aplicativo.interfaces;

import uepa.aplicativo.message.Message;

public interface Notifiable {
    void receiveMessage(Message m);
}
