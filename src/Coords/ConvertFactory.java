package Coords;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;
/**class ConvertFactor - Converts points from GPS to pixel and from pixel to gps,
 * and calculates distance between two pixel points (calculates distance on GPS).
 *Depends on the link-https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
 * @author Adi and Naomi.
 *
 */
public class ConvertFactory {


	private BufferedImage myImg;
	static  int mapWidth , mapHeight;
	// offsets
	static final double mapLongitudeStart =35.212405, mapLatitudeStart =32.106046 ;
	// length of map in long/lat
	static final double mapLongitude = 35.202574-mapLongitudeStart, 
			// invert because it decreases as you go down
			mapLatitude = mapLatitudeStart -32.101858;


	/**constructor */
	public ConvertFactory(BufferedImage img){
		this.myImg = img;
		this.mapWidth=myImg.getWidth();
		this.mapHeight=myImg.getHeight();


	}

	/**
	 * function GpsToPicsel - Gets a GPS point and converts it to a pixel point in the image.
	 * @param gps- gps point
	 * @return - pixel point
	 */
	public static Point3D GpsToPicsel(Point3D gps) {
		// use offsets
		gps.set_y( gps.y() - mapLongitudeStart);
		// do inverse because the latitude increases as we go up but the y decreases as we go up.
		// if we didn't do the inverse then all the y values would be negative.
		gps.set_x( mapLatitudeStart-gps.x());

		// set x & y using conversion
		int x = (int) (mapWidth*(gps.y()/mapLongitude));
		int y = (int) (mapHeight*(gps.x()/mapLatitude));

		return new Point3D(x, y);
	}
	/**
	 * function PicselToGps - Gets a pixel point and converts it to GPS point
	 * @param picsel
	 * @return- gps point
	 */
	public static Point3D PicselToGps(Point3D picsel) {
		double y= ((mapLongitude*picsel.x())/mapWidth)+mapLongitudeStart;
		double x= -((mapLatitude*picsel.y() )/mapHeight)+mapLatitudeStart;
		return new Point3D(x,y);
	}
	/**
	 * function distancePicsel-Calculates the distance between two pixel points by converting 
	 * to GPS point and calculating distance and conversion DOUBLE 
	 * @param picsel0- point 0
	 * @param picsel1 - point 1
	 * @return double of distance
	 */
	public static double distancePicsel(Point3D picsel0,Point3D picsel1) {
		MyCoords myCoords= new MyCoords();
		Point3D gps0=PicselToGps(picsel0);
		Point3D gps1=PicselToGps(picsel1);
		return myCoords.distance3d(gps0, gps1);			
	}
	public void setMyImg(BufferedImage myImg) {
		this.myImg = myImg;
	}
	public Image getMyImg() {
		return myImg;
	}
	

}


