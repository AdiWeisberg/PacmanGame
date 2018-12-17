package GIS;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * class project - A collection of layers sustained by an array of layers.
 * @author Naomi and Adi
 *@implements GIS_project
 */
public class project implements GIS_project {

	private ArrayList<GIS_layer> layers;
	private Meta_data data;
	
	 /** constructor 
 * @throws ParseException - Because you convert String information to a date then it can bounces an error if you can not convert*/
	public project() throws ParseException{
		layers=new  ArrayList<GIS_layer>();
		this.data = new System_data();
		}
	/**constructor that Get an array list of layers and put it into its layer.
	 * @param layers -An array that is deeply reproduced*/
	public project(ArrayList<GIS_layer> layers){
		this.layers = new ArrayList<GIS_layer>();
		this.layers.addAll(layers);
	}
	@Override
	public boolean add(GIS_layer e) {
		return layers.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		if(c.isEmpty())
			return false;
		return layers.addAll(c);
	}

	@Override
	public void clear() {
		layers.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return layers.retainAll(c);
	}

	@Override
	public int size() {
		return layers.size();
	}

	@Override
	public Object[] toArray() {
		return layers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return layers.toArray(a);
	}
	/**get_Meta_data - @return Meta_data - of the project-name,time.. */
	@Override
	public Meta_data get_Meta_data() {
		return this.data;
	}
	public ArrayList<GIS_layer> getLayers() {
		return layers;
	}


}