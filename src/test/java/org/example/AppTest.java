package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.stream.Collectors;

class AppTest {
  String path;

  @BeforeEach
  void setup() throws IOException {
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource resource = resolver.getResources("classpath*:org/example/バリューオブジェクト/**/*.class")[0];
    path = resource.getURL().toString().replaceFirst(".*/test-classes/", "");
    System.out.println(path);
    System.out.println(path.length());
  }

  @Test
  void successRunningOnSpring5() {
    // o  r  g  /  e  x  a  m  p  l  e  /  バ        リ    ュ   ー   オ    ブ        ジ        ェ    ク   ト    /  ジ        ド        ウ    シ   ャ    .  c  l  a  s  s
    // 6f,72,67,2f,65,78,61,6d,70,6c,65,2f,30d0     ,30ea,30e5,30fc,30aa,30d6     ,30b8     ,30a7,30af,30c8,2f,30b8     ,30c9     ,30a6,30b7,30e3,2e,63,6c,61,73,73
    System.out.println(path.chars().mapToObj(Integer::toHexString).collect(Collectors.joining(",")));

    Assertions.assertEquals(34, path.length());
    Assertions.assertEquals("org/example/バリューオブジェクト/ジドウシャ.class", path);
  }

  @Test
  void successRunningOnSpring6() {
    // o  r  g  /  e  x  a  m  p  l  e  /  ハ    ゙  リ    ュ   ー   オ    ブ        シ    ゙   ェ    ク   ト    /  シ   ゙   ト   ゙    ウ    シ   ャ    .  c  l  a  s  s
    // 6f,72,67,2f,65,78,61,6d,70,6c,65,2f,30cf,3099,30ea,30e5,30fc,30aa,30d5,3099,30b7,3099,30a7,30af,30c8,2f,30b7,3099,30c8,3099,30a6,30b7,30e3,2e,63,6c,61,73,73
    System.out.println(path.chars().mapToObj(Integer::toHexString).collect(Collectors.joining(",")));

    Assertions.assertEquals(39, path.length());
    Assertions.assertEquals("org/example/バリューオブジェクト/ジドウシャ.class", path);
  }
}
