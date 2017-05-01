package com.sorashiro.sorashirotools;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Sora
 * @date 2016.4.29
 * <p>
 * BlueTooth util class.
 * 蓝牙工具类。
 */

public class BlueToothUtil {

    /**
     * 暂时没有什么用的变量
     */
    private static boolean sIfOpenedBlueTooth = false;

    public static boolean isIfOpenedBlueTooth() {
        return sIfOpenedBlueTooth;
    }

    public static void setIfOpenedBlueTooth(boolean ifOpenedBlueTooth) {
        sIfOpenedBlueTooth = ifOpenedBlueTooth;
    }

    /**
     * 当前 Android 设备是否支持 Bluetooth
     *
     * @return true：支持 Bluetooth false：不支持 Bluetooth
     */
    public static boolean isBluetoothSupported() {
        return BluetoothAdapter.getDefaultAdapter() != null ? true : false;
    }

    /**
     * 当前 Android 设备的 bluetooth 是否已经开启
     *
     * @return true：Bluetooth 已经开启 false：Bluetooth 未开启
     */
    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }

        return false;
    }

    /**
     * 强制开启当前 Android 设备的 Bluetooth
     *
     * @return true：强制打开 Bluetooth　成功　false：强制打开 Bluetooth 失败
     */
    public static boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }

        return false;
    }

    /**
     * 强制关闭当前 Android 设备的 Bluetooth
     *
     * @return true：强制关闭 Bluetooth　成功　false：强制关闭 Bluetooth 失败
     */
    public static boolean turnOffBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.disable();
        }

        return false;
    }

    /**
     * 获取已配对蓝牙设备，并返回列表，注意要自己写一个Adapter
     *
     * @param mBluetoothAdapter Activity传来一个BluetoothAdapter
     * @return
     */
    public static ArrayList<Map<String, Object>> getData(BluetoothAdapter mBluetoothAdapter) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        if (devices.size() > 0) {
            for (Iterator<BluetoothDevice> iterator = devices.iterator(); iterator.hasNext(); ) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) iterator.next();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("bluetooth_service_name", bluetoothDevice.getName());
                map.put("bluetooth_service_addr", bluetoothDevice.getAddress());
                list.add(map);
            }
        }

        return list;
    }


    /**
     * ListAdapter参考内部类，这个类应该放进使用的Activity中
     */
    public class ServiceListAdapter extends BaseAdapter {
        private Context                        context;
        private LayoutInflater                 mInflater;
        private ArrayList<Map<String, Object>> arraylist;

        public ServiceListAdapter(Context context, ArrayList<Map<String, Object>> arraylist) {
            this.context = context;
            this.arraylist = arraylist;
            mInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return arraylist.size();
        }

        @Override
        public Object getItem(int position) {
            return arraylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                //convertView = mInflater.inflate(R.layout.list_service, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(arraylist.get(position).get("list_service_name").toString());
            viewHolder.address.setText(arraylist.get(position).get("list_service_address").toString());
            return convertView;
        }

        public class ViewHolder {
            //@Bind(R.id.list_service_name)
            TextView name;
            //@Bind(R.id.list_service_address)
            TextView address;

            public ViewHolder(View view) {
                //ButterKnife.bind(this, view);
            }
        }
    }

}