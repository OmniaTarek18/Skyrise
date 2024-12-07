import ReviewCard from "../../components/adminDashboard/Review/ReviewCard";
import "./feedback.css"
const Post = ({ post }) => {
    return (

        <div key={post.id} className="feedback-card">
            <ReviewCard
                userName={post.userName}
                source={post.flightSource}
                destination={post.flightDestination}
                date={new Date(post.flightDepartureDate).toLocaleDateString()}
                starRating={post.stars}
                ratings={{
                    comfort: post.comfort,
                    service: post.service,
                    punctuality: post.punctuality,
                    foodAndBeverage: post.foodAndBeverage,
                    cleanliness: post.cleanliness,
                }}
                comments={post.comment}
            />

        </div>
    );
};

export default Post;