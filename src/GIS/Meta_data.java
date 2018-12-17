package GIS;

import java.awt.Color;

import Geom.Point3D;

public interface Meta_data {
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC();
	/** return a String representing this data */
	public String toString();
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation();
	public int getName();
	public String getType();
	public int getSpeedweight();
	public int getRadius();
	public void setName(int name);
}
