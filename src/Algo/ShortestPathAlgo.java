package Algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import Coords.Path;
import GIS.GIS_element;
import GameElements.Game;

public class ShortestPathAlgo implements Set<Path> {
private ArrayList<Path> paths;



public ShortestPathAlgo() {
	paths= new ArrayList<Path>();
}
//main
	public static void main(String[] args) {
		new Game();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean add(Path arg0) {
		return paths.add(arg0) ;
	}
	@Override
	public boolean addAll(Collection<? extends Path> arg0) {
		return paths.addAll(arg0);
	}
	@Override
	public void clear() {
		paths.clear();		
	}
	@Override
	public boolean contains(Object arg0) {
		return paths.contains(arg0);
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return paths.containsAll(arg0);
	}
	@Override
	public boolean isEmpty() {
		return paths.isEmpty();
	}
	@Override
	public Iterator<Path> iterator() {
		return paths.iterator();
	}
	@Override
	public boolean remove(Object arg0) {
		return paths.remove(arg0);
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return paths.removeAll(arg0);
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return paths.retainAll(arg0);
	}
	@Override
	public int size() {
		return paths.size();
	}
	@Override
	public Object[] toArray() {
		return paths.toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		return paths.toArray(arg0);
	}

}
