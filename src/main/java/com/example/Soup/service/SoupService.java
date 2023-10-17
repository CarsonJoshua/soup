package com.example.Soup.service;

import com.example.Soup.model.Soup;
import com.example.Soup.model.SoupData;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SoupService {

    private final String c = "soup_list";



    public List<Soup> getSoups() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        List<Soup> soups = new ArrayList<>();
       for(DocumentReference soupDoc : db.collection(c).listDocuments()){
           DocumentSnapshot soup = soupDoc.get().get();
           if(soup.exists()){
//               System.out.println(soup);
               soups.add(new Soup(soup.getId(), soup.toObject(SoupData.class)));
           }
       }
       return soups;
    }
    public String validateFormSubmit(String soupName, String soupDesc, String imageAddress){
        //TODO form validation


        return null;
    }
    public void addSoup(String soupName, String soupDesc, String imageAddress){
        Firestore db = FirestoreClient.getFirestore();
        db.collection(c).document().set(new SoupData(soupName, soupDesc, imageAddress));
    }
    public void deleteSoup(String soupId){
        Firestore db = FirestoreClient.getFirestore();
        db.collection(c)
                .document(soupId)
                .delete();
    }
    public Soup getSoupById(String soupId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot doc = db.collection(c)
                .document(soupId)
                .get().get();
        if(doc.exists()){
            return new Soup(doc.getId(), doc.toObject(SoupData.class));
        }
        return null;
    }
    public void editSoup(String soupId, String soupName, String soupDesc, String imageAddress){
        Firestore db = FirestoreClient.getFirestore();
        SoupData sd = new SoupData(soupName, soupDesc, imageAddress);
        db.collection(c)
                .document(soupId)
                .set(sd);
    }

}
