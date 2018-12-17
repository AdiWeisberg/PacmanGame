package GameElements;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import GIS.GIS_element;
import GIS.Meta_data;
import GIS.info;
import Geom.Geom_element;
import Geom.Point3D;

public class Pacman implements GIS_element {
	private Point3D point;
	private Meta_data data;
	
	public Pacman(String type,int name,Point3D point, int speed,int radiusEat ) throws ParseException{
		data= new info(type,name,speed,radiusEat);
		this.point = new Point3D(point);
	}
	public Pacman(Point3D p) throws ParseException {
		this.point=p;
		data= new info("p",1,1,1);
	}
	public Pacman(GIS_element pacman) {
		this.point=pacman.getPoint();
		this.data = pacman.getData();
	}
	public Pacman(Point3D point,Meta_data newData) {
		this.data=newData;
		this.point=point;
	}
	public void addMeta_data(Meta_data newData) {
		this.data=newData;
		this.point=newData.get_Orientation();
	}
	public Point3D getPoint() {
		return point;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}

	public Meta_data getData() {
		return data;
	}

	public void setData(Meta_data data) {
		this.data = data;
	}

	@Override
	public Geom_element getGeom() {
		return this.point;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		
	
}
}