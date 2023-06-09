package com.winfred.item.rest;

import com.winfred.common.entity.BaseResponse;
import com.winfred.item.api.ItemInfoApi;
import com.winfred.item.entity.ItemInfo;
import com.winfred.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ItemInfoRest implements ItemInfoApi {

  @Autowired
  private ItemService itemService;

  @Override
  public BaseResponse<ItemInfo> getItemInfo(@PathVariable(value = "item_sn") String itemSn) {
    ItemInfo item = itemService.getItem(itemSn);
    return BaseResponse.success(item);
  }

  @Override
  public BaseResponse<List<ItemInfo>> getItemList(@PathVariable(value = "max") Integer max) {
    List<ItemInfo> itemInfos = itemService.mockItemList(max);
    return BaseResponse.success(itemInfos);
  }
}
