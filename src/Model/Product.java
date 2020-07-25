package Model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Product implements ObservableList<Part> {
    public ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    private int productID;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;

    public Product(int productID, String name, double price,int inv, int min, int max) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    public Product(int id, String name, int inv, Double price, int min, int max) {

    }

    public int getId() {
        return productID;
    }

    public void setId(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part partAdded) {
        if (partAdded != null) {
            associatedPart.add(partAdded);
        }
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedPart.remove(selectedAssociatedPart);
        int i;
        for(i = 0; i <associatedPart.size(); i++)
        {
        }
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedPart;
    }

    @Override
    public void addListener(ListChangeListener<? super Part> listener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super Part> listener) {

    }

    @Override
    public boolean addAll(Part... elements) {
        return false;
    }

    @Override
    public boolean setAll(Part... elements) {
        return false;
    }

    @Override
    public boolean setAll(Collection<? extends Part> col) {
        return false;
    }

    @Override
    public boolean removeAll(Part... elements) {
        return false;
    }

    @Override
    public boolean retainAll(Part... elements) {
        return false;
    }

    @Override
    public void remove(int from, int to) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<Part> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(Part part) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Part> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Part> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Part get(int index) {
        return null;
    }

    @Override
    public Part set(int index, Part element) {
        return null;
    }

    @Override
    public void add(int index, Part element) {

    }

    @Override
    public Part remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NotNull
    @Override
    public ListIterator<Part> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<Part> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<Part> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}