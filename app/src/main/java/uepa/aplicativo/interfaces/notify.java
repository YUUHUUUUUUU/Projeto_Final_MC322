package uepa.aplicativo.interfaces;

import uepa.aplicativo.message.Message;

public interface notify {
    void notifyListeners(Message m);
}
