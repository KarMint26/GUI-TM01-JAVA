package com.example.projectjsonapi;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.projectjsonapi.connect.ConnectURI;
import com.example.projectjsonapi.connect.ModelFarma;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ResultJSON {
    private int hargaTertinggi;
    private int jumlahData;

    public ResultJSON(int hargaTertinggi, int jumlahData) {
        this.hargaTertinggi = hargaTertinggi;
        this.jumlahData = jumlahData;
    }

    public ResultJSON(){

    }

    public int getHargaTertinggi() {
        return hargaTertinggi;
    }

    public void setHargaTertinggi(int hargaTertinggi) {
        this.hargaTertinggi = hargaTertinggi;
    }

    public int getJumlahData() {
        return jumlahData;
    }

    public void setJumlahData(int jumlahData) {
        this.jumlahData = jumlahData;
    }

    public void result() throws IOException {
        int semuaData = 0;
        int hargaMax = 0;
        int count = 0;

        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);

        assert response != null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ModelFarma> modelFarmas = new ArrayList<>();
        for(int i = 0; i < responseJSON.toArray().length; i++) {
            ModelFarma resModel = new ModelFarma();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resModel.setI_code((String) myJSONObject.get("i_code"));
            resModel.setG_code((String) myJSONObject.get("g_code"));
            resModel.setI_supp((String) myJSONObject.get("i_supp"));
            resModel.setI_barcode((String) myJSONObject.get("i_barcode"));
            resModel.setI_name((String) myJSONObject.get("i_name"));
            resModel.setI_brands((String) myJSONObject.get("i_brands"));
            resModel.setI_qty((String) myJSONObject.get("i_qty"));
            resModel.setI_qtymin((String) myJSONObject.get("i_qtymin"));
            resModel.setI_sell((String) myJSONObject.get("i_sell"));
            resModel.setI_unit((String) myJSONObject.get("i_unit"));
            resModel.setI_size((String) myJSONObject.get("i_size"));
            resModel.setI_color((String) myJSONObject.get("i_color"));
            resModel.setI_article((String) myJSONObject.get("i_article"));
            resModel.setI_cogs((String) myJSONObject.get("i_cogs"));
            resModel.setI_kdsell((String) myJSONObject.get("i_kdsell"));
            resModel.setI_status((String) myJSONObject.get("i_status"));
            resModel.setI_id((String) myJSONObject.get("id"));
            modelFarmas.add(resModel);
        }

        System.out.println("\nResponse are : ");
        for (int i = 0; i < modelFarmas.size(); i++) {
            System.out.println("\nNAME : " + modelFarmas.get(i).getI_name());
            System.out.println("BRANDS : " + modelFarmas.get(i).getI_brands());
            System.out.println("QUANTITY : " + modelFarmas.get(i).getI_qty());
            System.out.println("PRICE SELL : " + modelFarmas.get(i).getI_sell());

            int hargaObat = Integer.parseInt(modelFarmas.get(i).getI_sell());

            if(count == 0){
                hargaMax = Integer.parseInt(modelFarmas.get(0).getI_sell());
            }

            if(hargaObat <= 2000){
                count++;
                if(hargaObat > hargaMax){
                    hargaMax = hargaObat;
                }
            }
            semuaData = count;
        }
        setJumlahData(semuaData);
        setHargaTertinggi(hargaMax);
    }

}
