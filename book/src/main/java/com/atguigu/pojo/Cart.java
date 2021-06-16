package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: Cart
 * @Author ChenShu
 * @Date: 2021/6/13 12:09
 * @Version 1.0
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();

    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());

        if (item==null){
            //之前没添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else {
           item.setCount(item.getCount()+1);
           item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }
    public void deleteItem(Integer id){
        items.remove(id);
    }
    public void clear(){
        items.clear();
    }
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if (item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }


    public Integer getTotalCount() {
        Integer totalCount=0;

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }

        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        Integer totalCount=
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
          totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
