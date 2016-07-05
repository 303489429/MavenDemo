package com;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HttpParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		extracLinks("https://www.tenpay.com/app/wsm/wsm_pay_req_code.cgi?sign=2CBC6BBC9152AC7614E91B2F5B8C7B79&body=008%E7%BD%91%E8%B4%B9%E5%85%85%E5%80%BC&total_fee=1&spbill_create_ip=127.0.0.1&notify_url=http%3A%2F%2F10.34.43.50%3A8080%2FtenPayCallBack.html&input_charset=UTF-8&partner=1900000109&fee_type=1&out_trade_no=JSAPPSVR0115042400000008&return_url=http%3A%2F%2F10.34.43.50%3A8080%2FpayReturn.html#none");
	}

	// 获取一个网页上所有的链接和图片链接
		public static void extracLinks(String url) {
			try {
				Parser parser = new Parser(url);
				parser.setEncoding("UTF-8");
	//过滤 <frame> 标签的 filter，用来提取 frame 标签里的 src 属性所、表示的链接
				NodeFilter frameFilter = new NodeFilter() {
					public boolean accept(Node node) {
						if (node.getText().startsWith("frame src=")) {
							return true;
						} else {
							return false;
						}
					}
				};
	//OrFilter 来设置过滤 <a> 标签，<img> 标签和 <frame> 标签，三个标签是 or 的关系
		 OrFilter rorFilter = new OrFilter(new NodeClassFilter(LinkTag.class), new 
	NodeClassFilter(ImageTag.class));
		 OrFilter linkFilter = new OrFilter(rorFilter, frameFilter);
		//得到所有经过过滤的标签
		NodeList list = parser.extractAllNodesThatMatch(linkFilter);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof LinkTag)//<a> 标签 
			{
				LinkTag link = (LinkTag) tag;
				String linkUrl = link.getLink();//url
				String text = link.getLinkText();//链接文字
				System.out.println(linkUrl + "**********" + text);
			}
			else if (tag instanceof ImageTag)//<img> 标签
			{
				ImageTag image = (ImageTag) list.elementAt(i);
				System.out.print(image.getImageURL() + "********");//图片地址
				System.out.println(image.getText());//图片文字
			}
			else//<frame> 标签
			{
	//提取 frame 里 src 属性的链接如 <frame src="test.html"/>
				String frame = tag.getText();
				int start = frame.indexOf("src=");
				frame = frame.substring(start);
				int end = frame.indexOf(" ");
				if (end == -1)
					end = frame.indexOf(">");
				frame = frame.substring(5, end - 1);
				System.out.println(frame);
			}
		}
	} catch (ParserException e) {
				e.printStackTrace();
	}
	}
}
