/*
 *    Copyright (C) 2013 eXo Platform SAS.
 *
 *    This is free software; you can redistribute it and/or modify it
 *    under the terms of the GNU Lesser General Public License as
 *    published by the Free Software Foundation; either version 2.1 of
 *    the License, or (at your option) any later version.
 *
 *    This software is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this software; if not, write to the Free
 *    Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *    02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.codenvy.dashboard.pig.store;

import com.mongodb.DBObject;

import org.apache.pig.data.Tuple;

/**
 * Represents common interface for tuple transformation. Implementation
 * depends on specific type of Pig-latin script. 
 * 
 * @author <a href="mailto:abazko@exoplatform.com">Anatoliy Bazko</a>
 */
public interface TupleTransformer
{
   /**
    * Transforms {@link Tuple} into {@link DBObject} to be able to write 
    * to MongoDB.
    */
   DBObject transform(Tuple tuple);
}
