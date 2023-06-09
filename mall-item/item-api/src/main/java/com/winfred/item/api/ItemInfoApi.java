package com.winfred.item.api;

import com.winfred.common.entity.BaseResponse;
import com.winfred.item.entity.ItemInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/info")
public interface ItemInfoApi {

  @RequestMapping(value = "/{item_sn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  BaseResponse<ItemInfo> getItemInfo(@PathVariable(value = "item_sn") String itemSn);

  @RequestMapping(value = "/mock-list/{max}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  BaseResponse<List<ItemInfo>> getItemList(@PathVariable(value = "max") Integer max);
}
