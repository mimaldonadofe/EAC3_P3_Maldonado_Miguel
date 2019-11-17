package miguelmaldonado.ioc.eac3_p3_maldonado_miguel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable simpsonsTitolAnimation;
    ImageView simpsonsTitol;
    ImageView engVermell;
    ImageView engVerd;
    ImageView engBlau;
    ImageView ull;
    ImageView donut;
    MediaPlayer musica = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpsonsTitol = (ImageView) findViewById(R.id.simpsons_titol);
        // Afegim la imatge al imageView.
        simpsonsTitol.setBackgroundResource(R.drawable.simpsons_titol);
        // Creem l'animació i la iniciem.
        simpsonsTitolAnimation = (AnimationDrawable)simpsonsTitol.getBackground();
        simpsonsTitolAnimation.start();
        engVermell = (ImageView) findViewById(R.id.eng_vermell);
        engVerd = (ImageView) findViewById(R.id.eng_verd);
        engBlau = (ImageView) findViewById(R.id.eng_blau);
        ull = (ImageView) findViewById(R.id.ull);
        donut = (ImageView) findViewById(R.id.donut);
        // Els fem inicialment invisibles;
        engVermell.setVisibility(View.INVISIBLE);
        engVerd.setVisibility(View.INVISIBLE);
        engBlau.setVisibility(View.INVISIBLE);
        ull.setVisibility(View.INVISIBLE);
        donut.setVisibility(View.INVISIBLE);


        // Afegim el listener a la imatge del titol.
        simpsonsTitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprovem si els elements són visibles/invisibles i els fem invisibles/visibles.
                // Si són visibles aturem l'animació i amaguem, en cas contrari els fem visibles i
                // iniciem l'animació.
                if (engVermell.getVisibility() == View.VISIBLE){
                    engVermell.clearAnimation();
                    engVermell.setVisibility(View.INVISIBLE);
                }else{
                    engVermell.setVisibility(View.VISIBLE);
                    anima(engVermell,"antihorari");
                }
                if (engVerd.getVisibility() == View.VISIBLE){
                    engVerd.clearAnimation();
                    engVerd.setVisibility(View.INVISIBLE);
                }else{
                    engVerd.setVisibility(View.VISIBLE);
                    anima(engVerd,"horari");
                }
                if (engBlau.getVisibility() == View.VISIBLE){
                    engBlau.clearAnimation();
                    engBlau.setVisibility(View.INVISIBLE);
                }else{
                    engBlau.setVisibility(View.VISIBLE);
                    anima(engBlau,"antihorari");
                }
                if (ull.getVisibility() == View.VISIBLE){
                    ull.clearAnimation();
                    ull.setVisibility(View.INVISIBLE);
                }else{
                    ull.setVisibility(View.VISIBLE);
                    anima(ull,"rotUll");
                }
                if (donut.getVisibility() == View.VISIBLE){
                    donut.clearAnimation();
                    donut.setVisibility(View.INVISIBLE);
                }else{
                    donut.setVisibility(View.VISIBLE);
                    anima(donut,"movDonut");
                }
            }
        });

        // Afegim listener a la imatge del donut.
        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musica==null){
                    // Inicialitzem el reproductor.
                    musica = MediaPlayer.create(getApplicationContext(), R.raw.the_simpsons);
                    musica.start();
                }else{
                    if (musica.isPlaying()){
                        // Si està reproduïnt l'aturem.
                        musica.stop();
                        musica.release();
                        musica=null;
                    }else{
                        // Si no, l'iniciem (per exemple si s'ha acabat la reproducció sense
                        // aturar-la).
                        musica.start();
                    }
                }
            }
        });
    }

    /**
     * Mètode que afegeix una animació a una imatge i la inicia.
     * @param imatge
     * @param tipus: horari, antihorari, rotUll i movDonut.
     */
    private void anima(ImageView imatge,String tipus){
        Animation animacio=null;
        switch (tipus){
            case "horari":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rotacio_horaria);
                break;
            case "antihorari":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rotacio_antihoraria);
                break;
            case "movDonut":
                animacio = AnimationUtils.loadAnimation(this,R.anim.moviment_donut);
                break;
            case "rotUll":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rotacio_ull);
                break;
        }
        imatge.startAnimation(animacio);

    }
}
