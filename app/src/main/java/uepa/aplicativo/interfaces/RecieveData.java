package uepa.aplicativo.interfaces;

import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.extracurricular.Extracurricular;

public interface RecieveData {
    public void receiveData(Data data);
    public void receiveData(Data data, Extracurricular extracurricular);
    public void setData(Data data);
}
