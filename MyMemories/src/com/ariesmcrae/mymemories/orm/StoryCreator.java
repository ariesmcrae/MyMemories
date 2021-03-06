/*
The MyMemories source code (henceforth referred to as "MyMemories") is
copyrighted by Mike Walker, Adam Porter, Doug Schmidt, and Jules White
at Vanderbilt University and the University of Maryland, Copyright (c)
2014, all rights reserved.  Since MyMemories is open-source, freely
available software, you are free to use, modify, copy, and
distribute--perpetually and irrevocably--the source code and object code
produced from the source, as well as copy and distribute modified
versions of this software. You must, however, include this copyright
statement along with any code built using MyMemories that you release. No
copyright statement needs to be provided if you just ship binary
executables of your software products.

You can use MyMemories software in commercial and/or binary software
releases and are under no obligation to redistribute any of your source
code that is built using the software. Note, however, that you may not
misappropriate the MyMemories code, such as copyrighting it yourself or
claiming authorship of the MyMemories software code, in a way that will
prevent the software from being distributed freely using an open-source
development model. You needn't inform anyone that you're using MyMemories
software in your software, though we encourage you to let us know so we
can promote your project in our success stories.

MyMemories is provided as is with no warranties of any kind, including
the warranties of design, merchantability, and fitness for a particular
purpose, noninfringement, or arising from a course of dealing, usage or
trade practice.  Vanderbilt University and University of Maryland, their
employees, and students shall have no liability with respect to the
infringement of copyrights, trade secrets or any patents by DOC software
or any part thereof.  Moreover, in no event will Vanderbilt University,
University of Maryland, their employees, or students be liable for any
lost revenue or profits or other special, indirect and consequential
damages.

MyMemories is provided with no support and without any obligation on the
part of Vanderbilt University and University of Maryland, their
employees, or students to assist in its use, correction, modification,
or enhancement.

The names Vanderbilt University and University of Maryland may not be
used to endorse or promote products or services derived from this source
without express written permission from Vanderbilt University or
University of Maryland. This license grants no permission to call
products or services derived from the MyMemories source, nor does it
grant permission for the name Vanderbilt University or
University of Maryland to appear in their names.
 */

package com.ariesmcrae.mymemories.orm;

import java.util.ArrayList;

import com.ariesmcrae.mymemories.provider.MyMemoriesSchema;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * StoryCreator is a helper class that does convenience functions for converting
 * between the Custom ORM objects, ContentValues, and Cursors.
 * 
 * @author Mike Walker
 * 
 */
public class StoryCreator {

	/**
	 * Create a ContentValues from a provided StoryData.
	 * 
	 * @param data
	 *            StoryData to be converted.
	 * @return ContentValues that is created from the StoryData object
	 */
	public static ContentValues getCVfromStory(final StoryData data) {
		ContentValues rValue = new ContentValues();
		rValue.put(MyMemoriesSchema.Story.Cols.LOGIN_ID, data.loginId);
		rValue.put(MyMemoriesSchema.Story.Cols.STORY_ID, data.storyId);
		rValue.put(MyMemoriesSchema.Story.Cols.TITLE, data.title);
		rValue.put(MyMemoriesSchema.Story.Cols.BODY, data.body);
		rValue.put(MyMemoriesSchema.Story.Cols.AUDIO_LINK, data.audioLink);
		rValue.put(MyMemoriesSchema.Story.Cols.VIDEO_LINK, data.videoLink);
		rValue.put(MyMemoriesSchema.Story.Cols.IMAGE_NAME, data.imageName);
		rValue.put(MyMemoriesSchema.Story.Cols.IMAGE_LINK, data.imageLink);
		rValue.put(MyMemoriesSchema.Story.Cols.TAGS, data.tags);
		rValue.put(MyMemoriesSchema.Story.Cols.CREATION_TIME, data.creationTime);
		rValue.put(MyMemoriesSchema.Story.Cols.STORY_TIME, data.storyTime);
		rValue.put(MyMemoriesSchema.Story.Cols.LATITUDE, data.latitude);
		rValue.put(MyMemoriesSchema.Story.Cols.LONGITUDE, data.longitude);
		return rValue;
	}

	/**
	 * Get all of the StoryData from the passed in cursor.
	 * 
	 * @param cursor
	 *            passed in cursor to get StoryData(s) of.
	 * @return ArrayList<StoryData\> The set of StoryData
	 */
	public static ArrayList<StoryData> getStoryDataArrayListFromCursor(Cursor cursor) {
		ArrayList<StoryData> rValue = new ArrayList<StoryData>();
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					rValue.add(getStoryDataFromCursor(cursor));
				} while (cursor.moveToNext() == true);
			}
		}
		return rValue;
	}

	/**
	 * Get the first StoryData from the passed in cursor.
	 * 
	 * @param cursor
	 *            passed in cursor
	 * @return StoryData object
	 */
	public static StoryData getStoryDataFromCursor(Cursor cursor) {

		long rowID = cursor.getLong(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.ID));
		long loginId = cursor.getLong(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.LOGIN_ID));
		long storyId = cursor.getLong(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.STORY_ID));
		String title = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.TITLE));
		String body = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.BODY));
		String audioLink = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.AUDIO_LINK));
		String videoLink = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.VIDEO_LINK));
		String imageName = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.IMAGE_NAME));
		String imageMetaData = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.IMAGE_LINK));
		String tags = cursor.getString(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.TAGS));
		long creationTime = cursor.getLong(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.CREATION_TIME));
		long storyTime = cursor.getLong(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.STORY_TIME));
		double latitude = cursor.getDouble(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.LATITUDE));
		double longitude = cursor.getDouble(cursor.getColumnIndex(MyMemoriesSchema.Story.Cols.LONGITUDE));

		// construct the returned object
		StoryData rValue = new StoryData(rowID, loginId, storyId, title, body, audioLink, videoLink, imageName, imageMetaData,
		        tags, creationTime, storyTime, latitude, longitude);

		return rValue;
	}
}
