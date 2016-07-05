//package interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {
//	//
//	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-startTime");
//	
//	/**
//	 * This implementation always returns <code>true</code>.
//	 */
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//	    throws Exception {
//		long beginTime = System.currentTimeMillis();  //开始时间
//		startTimeThreadLocal.set(beginTime); //线程绑定变量（该数据只有当前请求的线程可见）
//		return true; //继续流程
//	}
//
//
//	/**
//	 * This implementation is empty.
//	 */
//	public void afterCompletion(
//			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		long endTime = System.currentTimeMillis() ; //结束时间
//		long beginTime = startTimeThreadLocal.get(); //得到线程绑定的局部变量(开始时间)
//		long consumeTime  = endTime-beginTime ; //消耗时间
//		System.out.println(String.format("%s consume %d millis",request.getRequestURI(),consumeTime));
//	}
//}
