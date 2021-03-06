package cn.binarywang.wx.miniapp.bean.express.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 商品信息对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressOrderShop implements Serializable {
  private static final long serialVersionUID = 7256509453502211830L;

  /**
   * 商家小程序的路径
   * <pre>
   * 是否必填： 是
   * 描述： 建议为订单页面
   * </pre>
   */
  @SerializedName("wxa_path")
  private String wxaPath;

  /**
   * 商品缩略图url
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("img_url")
  private String imgUrl;

  /**
   * 商品名称
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_name")
  private String goodsName;

  /**
   * 商品数量
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("goods_count")
  private Integer goodsCount;

  /**
   * 商品详情列表
   * <pre>
   * 是否必填： 否
   * 描述： 适配多商品场景，用以消息落地页展示。（新规范，新接入商家建议用此字段）
   * </pre>
   */
  @SerializedName("detail_list")
  private List<WxMaExpressOrderShopDetail> detailList;

}
