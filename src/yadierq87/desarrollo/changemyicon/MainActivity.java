package yadierq87.desarrollo.changemyicon;


import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListarApks();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void ListarApks() {
    	
    	PackageManager pm = this.getPackageManager();  
	    Intent intent = new Intent(Intent.ACTION_MAIN, null);
	    intent.addCategory(Intent.CATEGORY_LAUNCHER);
	    List<Apks> datos_apk = new ArrayList<Apks>();
	    //Recuperamos las aplicaciones para las que el usuario tiene los permisos suficientes
	    List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);

	    for (ResolveInfo rInfo : list) {
	    	
	    	String nombre = rInfo.activityInfo.applicationInfo.loadLabel(pm).toString();
	    	String info   = rInfo.activityInfo.applicationInfo.dataDir.toString();
	    	Drawable ic_icon   = rInfo.activityInfo.applicationInfo.loadIcon(pm);
	    	
	    	Apks new_apk = new Apks(nombre, info, ic_icon);
	    	datos_apk.add(new_apk);
	    	//rInfo.activityInfo.applicationInfo.icon
		} 
    	
    	AdaptadorListApk adaptador =	new AdaptadorListApk(this,datos_apk);
		final ListView grdOpciones = (ListView)findViewById(R.id.list_installapk);
		grdOpciones.setAdapter(adaptador);    
		
		grdOpciones.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
			//Acciones necesarias al hacer click
				
				
				
			}
			});
		
		
	}
    
}

@SuppressWarnings("rawtypes")
class AdaptadorListApk extends ArrayAdapter{

	List<Apks> datos_apks = new ArrayList<Apks>();	
	Activity context;	
	
	@SuppressWarnings("unchecked")
	AdaptadorListApk(Activity context,List<Apks> apks) {
		super(context, R.layout.list_apk, apks);
		this.datos_apks = apks;
		this.context = context;		
		}
	
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View item = convertView;
			ViewHolder holder;
			if(item == null)			{
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.list_apk, null);
			holder = new ViewHolder();
			holder.name_apk = (TextView)item.findViewById(R.id.text_name);
			holder.info_apk = (TextView)item.findViewById(R.id.text_info);
			holder.icono = (ImageView)item.findViewById(R.id.image_icon);			
			item.setTag(holder);
			}
			else
			{		holder = (ViewHolder)item.getTag();			}	
			holder.name_apk.setText("" + datos_apks.get(position).getName_apk());
			holder.info_apk.setText("" + datos_apks.get(position).getInfo_apk());
			holder.icono.setImageDrawable(datos_apks.get(position).getIcono());						
		return(item);
		}	
		

}

 class ViewHolder {
	 //String name_apk;String info_apk; int icono;
 TextView name_apk;
 TextView info_apk; 
 ImageView icono; 
 }
