# Bit_Android_Chatting

android 기초 예제와 간단한 Chatting 프로그램
eclipse로 구현한 server와 client App

<h3>Technology</h3>
Java8, Tomcat 7, eclipse, Android Studio


<h3>다중 접속하면 안되는 문제점 (ServerApp01.java and ServerApp02.java)</h3>
- client와 server가 통신할 때 accept를 이미 한 상태에서 무한루프를 계속 돌면서 readLine을 읽기 때문에 새로운 client가 접속해도 이미 무한루프에 빠져있고 accept가 안되었기 때문에 server에 접근할 수 없다. </br>
- 이를 해결하기 위해서 Thread가 필요, client가 접속할 때마다 accept해주는 Thread를 생성해준다. </br>
- web의 경우 request, response하는 순간 Thread를 없애주지만, 상태유지 기술이 필요하다. </br>
- cs의 경우 상태유지는 되지만 Thread가 계속 살아있어서 서버에 부하가 올 수 있다. 프로세스가 하나지만 Thread가 많아서 부하가 오는것이다. 또한 web은 스레드가 계속 살아있지 않고 request올 때에만 Thread가 발생된다. 하지만 cs의 경우 client가 요청할 때까지 계속 Thread가 돌고 잇다.</br>
- 왜 client는 Thread가 되어 있는가 => 당연히 ui와 logic은 분리되어있어야함 </br>
- server는 정상 종료되면 null이며, 비정상 종료 되었을 때 SocketException이 발생한다. </br>
