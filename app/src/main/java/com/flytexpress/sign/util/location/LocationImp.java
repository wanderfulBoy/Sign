package com.flytexpress.sign.util.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import cn.trinea.android.common.util.ToastUtils;

/**
 * Created by Administrator on 2017/12/20.
 */

public class LocationImp {
    LocationManager locationManager;
    LocationListener locationListener;
    double lat;
    double lon;

    public Location getLocation(final Context context) {
        // 位置
        Location location;
        String contextService = Context.LOCATION_SERVICE;
        String provider;
        locationManager = (LocationManager) context.getSystemService(contextService);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
        criteria.setAltitudeRequired(false);// 不要求海拔
        criteria.setBearingRequired(false);// 不要求方位
        criteria.setCostAllowed(true);// 允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
        // 从可用的位置提供器中，匹配以上标准的最佳提供器
        provider = locationManager.getBestProvider(criteria, true);
        // 获得最后一次变化的位置
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "onCreate: 没有权限 ");
  //          return;
        }
        Log.e("TAG","location:"+location);
        locationListener = new LocationListener() {
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub
            }

            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
                ToastUtils.show(context,"看看："+locationManager
                        .getLastKnownLocation(provider));
            }

            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
            }

            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.e("TAG", "经度："+String.valueOf(lat));
                Log.e("TAG", "纬度："+String.valueOf(lon));
            }
        };
// 监听位置变化，2秒一次，距离10米以上
        locationManager.requestLocationUpdates(provider, 2000, 10,
                locationListener);
        return location;
    }

}
