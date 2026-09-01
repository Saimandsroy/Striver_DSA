import java.util.*;

class Tweet {

    int tweetId;
    int time;

    Tweet(int tweetId, int time) {
        this.tweetId = tweetId;
        this.time = time;
    }
}


class User {

    int userId;
    HashSet<Integer> following;
    LinkedList<Tweet> tweets;

    User(int userId) {

        this.userId = userId;

        following = new HashSet<>();
        tweets = new LinkedList<>();

        // User follows himself
        following.add(userId);
    }


    void addTweet(Tweet tweet) {
        tweets.addFirst(tweet);
    }


    void addFollowing(int userId) {
        following.add(userId);
    }


    void removeFollowing(int userId) {
        following.remove(userId);
    }
}


class FeedNode {

    int userId;
    int index;
    Tweet tweet;

    FeedNode(int userId, int index, Tweet tweet) {

        this.userId = userId;
        this.index = index;
        this.tweet = tweet;
    }
}


class Twitter {

    HashMap<Integer, User> userMap;
    int timeCounter;


    public Twitter() {

        userMap = new HashMap<>();
        timeCounter = 0;
    }


    public void postTweet(int userId, int tweetId) {

        // Create user if user doesn't exist
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }

        // Get user object
        User user = userMap.get(userId);

        // Increase time
        timeCounter++;

        // Create tweet
        Tweet tweet = new Tweet(tweetId, timeCounter);

        // Add tweet to user's LinkedList
        user.addTweet(tweet);
    }


    public List<Integer> getNewsFeed(int userId) {

        List<Integer> result = new ArrayList<>();

        // User doesn't exist
        if (!userMap.containsKey(userId)) {
            return result;
        }

        User user = userMap.get(userId);


        // Max Heap
        // Newer tweet comes first
        PriorityQueue<FeedNode> pq =
            new PriorityQueue<>(
                (a, b) -> b.tweet.time - a.tweet.time
            );


        // Add newest tweet of every followed user
        for (int followeeId : user.following) {

            User followee = userMap.get(followeeId);

            if (followee != null && !followee.tweets.isEmpty()) {

                Tweet newestTweet = followee.tweets.getFirst();

                pq.add(
                    new FeedNode(
                        followeeId,
                        0,
                        newestTweet
                    )
                );
            }
        }


        // Get maximum 10 tweets
        while (!pq.isEmpty() && result.size() < 10) {

            // Get newest tweet
            FeedNode current = pq.poll();

            // Add tweet ID to answer
            result.add(current.tweet.tweetId);


            // Get user who posted this tweet
            User tweetUser = userMap.get(current.userId);


            // Move to next older tweet
            int nextIndex = current.index + 1;


            // If another tweet exists
            if (nextIndex < tweetUser.tweets.size()) {

                Tweet nextTweet =
                    tweetUser.tweets.get(nextIndex);

                pq.add(
                    new FeedNode(
                        current.userId,
                        nextIndex,
                        nextTweet
                    )
                );
            }
        }


        return result;
    }


    public void follow(int followerId, int followeeId) {

        // Create follower if doesn't exist
        if (!userMap.containsKey(followerId)) {
            userMap.put(
                followerId,
                new User(followerId)
            );
        }


        // Create followee if doesn't exist
        if (!userMap.containsKey(followeeId)) {
            userMap.put(
                followeeId,
                new User(followeeId)
            );
        }


        // Get follower
        User follower = userMap.get(followerId);


        // Add followee
        follower.addFollowing(followeeId);
    }


    public void unfollow(int followerId, int followeeId) {

        // User doesn't exist
        if (!userMap.containsKey(followerId)) {
            return;
        }


        // User cannot unfollow himself
        if (followerId == followeeId) {
            return;
        }


        // Get follower
        User follower = userMap.get(followerId);


        // Remove followee
        follower.removeFollowing(followeeId);
    }
}