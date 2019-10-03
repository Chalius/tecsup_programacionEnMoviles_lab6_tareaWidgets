package com.example.lab6_tareawidgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class widgetTarea extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_tarea);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them



        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            fecha_gonzalo(context,appWidgetManager, appWidgetId);

            actualizarWidget(context,appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    public static void actualizarWidget(Context context, AppWidgetManager appWidgetManager, int widgetId){

        SharedPreferences datos = context.getSharedPreferences("DatosWidget",Context.MODE_PRIVATE);

        String ciudad = datos.getString("ciudad","Ciudad");
        String distrito = datos.getString("distrito","Distrito");

        RemoteViews controles = new RemoteViews(context.getPackageName(),R.layout.widget_tarea);
        controles.setTextViewText(R.id.txt_ubicacion,ciudad);
        controles.setTextViewText(R.id.txt_distrito,distrito);
        appWidgetManager.updateAppWidget(widgetId,controles);









    }


    public static void fecha_gonzalo(Context context, AppWidgetManager appWidgetManager, int widgetId){

        RemoteViews controles = new RemoteViews(context.getPackageName(),R.layout.widget_tarea);

        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm a");
        Date now = new Date();
        String hora = sdfDate.format(now);

        controles.setTextViewText(R.id.txt_hora,hora);



        SimpleDateFormat sdfFecha = new SimpleDateFormat("EEE \nMMM dd");
        String fecha = sdfFecha.format(now);
        controles.setTextViewText(R.id.txt_fecha,fecha);

        appWidgetManager.updateAppWidget(widgetId,controles);
    }


}

