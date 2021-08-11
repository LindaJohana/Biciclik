package com.example.biciclik.Trip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.DrawerMain.DrawerActivities;
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.TripResponseFinal;
import com.example.biciclik.utils.BikeTestActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class TripActivity extends Fragment implements TripInterfaces.activities {
    Button ButtonOkV, ButtonNo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TripPresenters presenter;
    LocalData localData;
    String home=null;
    String currentPhotoPath, UrlPhoto;
    File sel=null;
    public final int REQUEST_IMAGE_CAPTURE = 1;
    TextView txtPuntoIR, txtHoraIR,txtTiempoR, txtDestonoR, txtPuntoER, txtHoraER, txtAhorroR, txtHuellaR;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip, container, false);
        initObjects(view);
        Log.e("ONCREATE", "TRIP");
        presenter.getInfoTripPresenter();

        ButtonOkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.fotoTerminar)
                        .setPositiveButton(R.string.aceptarTerminar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                Toast.makeText(getContext(), R.string.infoFoto, Toast.LENGTH_SHORT).show();
                                capturePhoto();
                            }
                        })
                        .setNegativeButton(R.string.cancelarTerminar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });
        ButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new BikeTestActivity());
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private void initObjects(View view){
        txtPuntoIR = view.findViewById(R.id.txtPuntoIR);
        txtHoraIR = view.findViewById(R.id.txtHoraIR);
        txtTiempoR = view.findViewById(R.id.txtTiempoR);
        txtDestonoR = view.findViewById(R.id.txtDestonoR);
        txtPuntoER = view.findViewById(R.id.txtPuntoER);
        txtHoraER = view.findViewById(R.id.txtHoraER);
        txtHuellaR = view.findViewById(R.id.txtHuellaR);
        txtAhorroR = view.findViewById(R.id.txtAhorroR);
        ButtonOkV=view.findViewById(R.id.ButtonOkV);
        ButtonNo=view.findViewById(R.id.ButtonNo);
        presenter = new TripPresenters(this);
        localData = new LocalData();
        UrlPhoto ="";
    }

    @Override
    public void setTrip(TripResponseFinal data) {
        txtPuntoIR.setText(data.getStart_detail().getName());
        txtHoraIR.setText(data.getStart_date());
        txtTiempoR.setText(data.getTime_elapsed());
        txtDestonoR.setText(data.getDestination());
        txtPuntoER.setText(data.getDelivery_detail().getName());
        txtHoraER.setText(data.getDelivery_date());
//        txtHuellaR.setText(String.valueOf(data.getCarbon_footprint()));
//        txtAhorroR.setText(String.valueOf(data.getEconomic_savings()));
    }

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
                        "com.example.biciclik.provider",
                        photoFile);
                //                    i.putExtra("path_image", photoURI.toString());
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            FotoPerfil.setImageBitmap(imageBitmap);
            try (FileOutputStream out = new FileOutputStream(currentPhotoPath)){
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UrlPhoto = currentPhotoPath;
            presenter.sendStatusPresenter(UrlPhoto);
//            presenter.FinalImagePresenter(UrlSelfie);
        }else{
            Log.e("ELSE DE PHOTO RESULT",currentPhotoPath);
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
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getPath();
        return image;
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }

    @Override
    public void lanzarHome() {
        localData.CreateTrip();
        Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("home",home);
        startActivity(i);
    }

}
