package tp2.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleAndTripData {
    private Map<String, StaticVehicleData> staticVehicleDataMap = new HashMap<>();
    private Map<String, List<TripData>> tripDataMap = new HashMap<>();

    // Método para adicionar dados estáticos do veículo
    public void addStaticVehicleData(String vehId, StaticVehicleData staticData) {
        staticVehicleDataMap.put(vehId, staticData);
    }

    // Método para adicionar dados de viagem
    public void addTripData(String vehId, TripData tripData) {
        if (!tripDataMap.containsKey(vehId)) {
            tripDataMap.put(vehId, new ArrayList<>());
        }
        tripDataMap.get(vehId).add(tripData);
    }

    // Método para obter dados estáticos e de viagem
    public Map<String, Object> getVehicleAndTripData(String vehId) {
        Map<String, Object> result = new HashMap<>();
        if (staticVehicleDataMap.containsKey(vehId)) {
            result.put("Static Vehicle Data", staticVehicleDataMap.get(vehId));
        }
        if (tripDataMap.containsKey(vehId)) {
            result.put("Trip Data", tripDataMap.get(vehId));
        }
        return result;
    }
}
