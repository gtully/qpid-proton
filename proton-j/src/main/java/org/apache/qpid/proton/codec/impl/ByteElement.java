/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.apache.qpid.proton.codec.impl;

import java.nio.ByteBuffer;

import org.apache.qpid.proton.codec.Data;

class ByteElement extends AtomicElement<Byte>
{

    private final byte _value;

    ByteElement(Element parent, Element prev, byte b)
    {
        super(parent, prev);
        _value = b;
    }

    @Override
    public int size()
    {
        return isElementOfArray() ? 1 : 2;
    }

    @Override
    public Byte getValue()
    {
        return _value;
    }

    @Override
    public Data.DataType getDataType()
    {
        return Data.DataType.BYTE;
    }

    @Override
    public int encode(ByteBuffer b)
    {
        if(isElementOfArray())
        {
            if(b.hasRemaining())
            {
                b.put(_value);
                return 1;
            }
        }
        else
        {
            if(b.remaining()>=2)
            {
                b.put((byte)0x51);
                b.put(_value);
                return 2;
            }
        }
        return 0;
    }
}
