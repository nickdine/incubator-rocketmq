/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openmessaging.samples;


import org.apache.openmessaging.MessagingEndPoint;
import org.apache.openmessaging.MessagingEndPointManager;

import java.nio.charset.Charset;

public class Producer {
    public static void main(String[] args) {
        final MessagingEndPoint messagingEndPoint = MessagingEndPointManager.getMessagingEndPoint("openmessaging:rocketmq://localhost:10911/namespace");

        final org.apache.openmessaging.Producer producer = messagingEndPoint.createProducer();

        messagingEndPoint.start();

        producer.start();

        producer.send(producer.createBytesMessage("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                messagingEndPoint.shutdown();
            }
        }));
    }
}