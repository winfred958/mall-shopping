package com.winfred.item.service;

import com.winfred.item.entity.ItemInfo;

import java.util.List;

public interface ItemService {
  ItemInfo getItem(String itemSn);

  List<ItemInfo> mockItemList(int max);
}
