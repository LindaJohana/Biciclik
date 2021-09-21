package com.colombiagames.biciclick.Profile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.objects.UserData;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileActivity extends Fragment implements ProfileInterfaces.activities{
    CircleImageView FotoPerfil;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};
    EditText nombre, email, direccion, edLastName;
    TextView empresa, telefono, txt16, txt17, txt18, txt19, txt20, txt21, txt22;
    Button buttonGuardar;
    ProfilePresenters presenter;
    ProfileData changedDataA;
    UserData changedUserA;
    String currentPhotoPath, UrlSelfie;
    File sel=null;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        initObjects(view);

        //letra verdana
        Typeface fuente = Typeface.createFromAsset(getActivity().getAssets(), "fonts/verdana.ttf");
        txt16.setTypeface(fuente);
        txt17.setTypeface(fuente);
        nombre.setTypeface(fuente);
        txt18.setTypeface(fuente);
        edLastName.setTypeface(fuente);
        txt19.setTypeface(fuente);
        telefono.setTypeface(fuente);
        txt20.setTypeface(fuente);
        email.setTypeface(fuente);
        txt21.setTypeface(fuente);
        empresa.setTypeface(fuente);
        txt22.setTypeface(fuente);
        direccion.setTypeface(fuente);
        buttonGuardar.setTypeface(fuente);

        int requestCode = 200;
        presenter.getProfilePresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        FotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });
        return view;
    }
    public void initObjects(View view){
        FotoPerfil=view.findViewById(R.id.fotoPerfil);
        nombre=view.findViewById(R.id.edNombre);
        telefono=view.findViewById(R.id.edTelefono);
        email=view.findViewById(R.id.edEmail);
        direccion=view.findViewById(R.id.edDireccion);
        empresa=view.findViewById(R.id.tvEmpresa);
        buttonGuardar=view.findViewById(R.id.buttonCambios);
        edLastName=view.findViewById(R.id.edLastName);
        UrlSelfie="";
        presenter=new ProfilePresenters(this);
        txt16=view.findViewById(R.id.txt16);
        txt17=view.findViewById(R.id.txt17);
        txt18=view.findViewById(R.id.txt18);
        txt19=view.findViewById(R.id.txt19);
        txt20=view.findViewById(R.id.txt20);
        txt21=view.findViewById(R.id.txt21);
        txt22=view.findViewById(R.id.txt22);
    }
    public void cargarImagen(){
        final CharSequence[] opciones={"Tomar foto","Cancelar"};
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(getContext());
        alertOptiones.setTitle("seleccione una opción");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
                    capturePhoto();
                }else {
                    dialog.dismiss();
                }
            }
        });
        alertOptiones.show();
    }

    public final int REQUEST_IMAGE_CAPTURE = 1;
    public void capturePhoto() {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getActivity().getPackageManager())!=null){
            File photoFile = null;
            try {
                photoFile = createImage();
                sel=photoFile;
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.colombiagames.biciclick.provider",
                        photoFile);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            FotoPerfil.setImageBitmap(imageBitmap);
            try (FileOutputStream out = new FileOutputStream(currentPhotoPath)){
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UrlSelfie = currentPhotoPath;
        }
    }
    public File createImage() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getPath();
        return image;
    }
    public void guardarCambios(){
        changedUserA=new UserData(nombre.getText().toString(), edLastName.getText().toString(),email.getText().toString());
        changedDataA=new ProfileData(changedUserA,direccion.getText().toString(), UrlSelfie);
        presenter.updatePresenter(changedUserA, changedDataA);
    }

    @Override
    public void setProfile(ProfileData data) {
        nombre.setText(data.getUser().getFirst_name());
        edLastName.setText(data.getUser().getLast_name());
        if (data.getSelfie().startsWith("http")){
            Picasso.with(BaseContext.getContext()).load(data.getSelfie()).into(FotoPerfil);
        }else {
            Picasso.with(BaseContext.getContext()).load(getString(R.string.server)+data.getSelfie()).into(FotoPerfil);

        }
        telefono.setText(data.getPhone_number());
        email.setText(data.getUser().getEmail());
        empresa.setText(data.getCompany_detail().getName());
        direccion.setText(data.getAddress());
    }

    @Override
    public void setErrorMessage(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }
}