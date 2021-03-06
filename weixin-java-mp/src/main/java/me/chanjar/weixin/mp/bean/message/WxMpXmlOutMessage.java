package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.builder.outxml.*;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;

import java.io.Serializable;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
public abstract class WxMpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = -381382011286216263L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "ToUserName")
  @JacksonXmlCData
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "FromUserName")
  @JacksonXmlCData
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  @JacksonXmlProperty(localName = "CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgType")
  @JacksonXmlCData
  protected String msgType;


  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Encrypt")
  @JacksonXmlCData
  private String encrypt;

  @XStreamAlias("MsgSignature")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgSignature")
  @JacksonXmlCData
  private String msgSignature;

  @XStreamAlias("TimeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "TimeStamp")
  @JacksonXmlCData
  private String timeStamp;

  @XStreamAlias("Nonce")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Nonce")
  @JacksonXmlCData
  private String nonce;

  /**
   * ??????????????????builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static MusicBuilder MUSIC() {
    return new MusicBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static TransferCustomerServiceBuilder TRANSFER_CUSTOMER_SERVICE() {
    return new TransferCustomerServiceBuilder();
  }

  /**
   * ??????????????????builder
   */
  public static DeviceBuilder DEVICE() {
      return new DeviceBuilder();
  }

  @SuppressWarnings("unchecked")
  public String toXml() {
    return XStreamTransformer.toXml((Class<WxMpXmlOutMessage>) this.getClass(), this);
  }

  /**
   * ????????????????????????
   */
  public WxMpXmlOutMessage toEncrypted(WxMpConfigStorage wxMpConfigStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
    WxCryptUtil.EncryptContext context = pc.encryptContext(plainXml);
    WxMpXmlOutMessage res = new WxMpXmlOutMessage() {};
    res.setNonce(context.getNonce());
    res.setEncrypt(context.getEncrypt());
    res.setTimeStamp(context.getTimeStamp());
    res.setMsgSignature(context.getSignature());
    return res;
  }

  /**
   * ??????????????????xml??????
   */
  public String toEncryptedXml(WxMpConfigStorage wxMpConfigStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
    return pc.encrypt(plainXml);
  }
}
