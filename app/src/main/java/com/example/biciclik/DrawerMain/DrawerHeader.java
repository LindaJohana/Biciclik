//package com.example.biciclik.DrawerMain;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.biciclik.BaseContext.BaseContext;
//import com.example.biciclik.Login.LoginActivities;
//import com.example.biciclik.R;
//import com.example.biciclik.objects.ProfileData;
//import com.squareup.picasso.Picasso;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//import static com.example.biciclik.BaseContext.BaseContext.getContext;
//
//public class DrawerHeader extends AppCompatActivity implements DrawerInterfaces.activitiesHeader{
//    DrawerPresenters presenter;
//    CircleImageView drawerSelfie;
//    TextView textViewUsuario, textViewEmailD;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.drawer_header);
//        presenter=new DrawerPresenters(null, this);
//        presenter.profileHeaderPresenter();
//        drawerSelfie=findViewById(R.id.drawerSelfie);
//        textViewUsuario=findViewById(R.id.textViewUsuarioD);
//        textViewEmailD=findViewById(R.id.textViewEmailD);
//    }
//
//    public void setProfileHeader(ProfileData data) {
//        textViewUsuario.setText(data.getUser().getFirst_name()+" "+data.getUser().getLast_name());
//        if (data.getSelfie().startsWith("http")){
//            Picasso.with(BaseContext.getContext()).load(data.getSelfie()).into(drawerSelfie);
//        }else {
//            Picasso.with(BaseContext.getContext()).load(getString(R.string.server)+data.getSelfie()).into(drawerSelfie);
//
//        }
//        textViewEmailD.setText(data.getUser().getEmail());
//    }
//
//
//    public void lanzarLogin() {
//        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
//        startActivity(i);
//    }
//}
