package uepa.aplicativo.interfaces;

import uepa.aplicativo.message.Message;

public interface Notificable {
    void receiveMessage(Message m);
}
