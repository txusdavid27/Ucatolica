package edu.puj.taller3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.puj.taller3.Model.Usuario;

public class DisponiblesAdapter extends ArrayAdapter<Usuario> {

    private static final String PATH_IMAGE = "images/";

    private Context context;
    private List<Usuario> usuarios;
    private StorageReference mStorage;
    private String currUserId;

    public DisponiblesAdapter(Context context, List<Usuario> usuarios, String currUserId){
        super(context, R.layout.disponibles, usuarios);
        this.context = context;
        this.usuarios = usuarios;
        this.mStorage = FirebaseStorage.getInstance().getReference();
        this.currUserId = currUserId;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = layoutInflater.from(this.context).inflate(R.layout.disponibles, viewGroup, false);
        TextView name = mView.findViewById(R.id.tvName);
        TextView apellido = mView.findViewById(R.id.tvLast);
        Button btnLoc = mView.findViewById(R.id.btnLocation);
        if(!this.currUserId.equalsIgnoreCase(this.usuarios.get(i).getKey())){
            btnLoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), MapsActivity.class);
                    intent.putExtra("key", usuarios.get(i).getKey());
                    view.getContext().startActivity(intent);
                }
            });
        } else {
            btnLoc.setText("Estoy Conectado!");
            btnLoc.setClickable(false);
        }

        ImageView ivPhoto = mView.findViewById(R.id.ivProfile);

        try {
            downloadFile(this.usuarios.get(i).getKey(), ivPhoto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(this.usuarios.get(i).getName());
        apellido.setText(this.usuarios.get(i).getApellido());

        return mView;
    }

    private void downloadFile(String id, final ImageView ivPhoto) throws IOException {
        final File localFile = File.createTempFile("images", "png");
        StorageReference imageRef = mStorage.child(PATH_IMAGE + id + "/profile.png");
        imageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        ivPhoto.setImageURI(Uri.fromFile(localFile));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }
}
