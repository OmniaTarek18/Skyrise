import React from 'react';
import { Award } from 'lucide-react';

const FeedbackBadge = () => {
  return (
    <div className="feedback-badge">
      <div className="badge-inner">
        <Award size={32} className="badge-icon" />
        <span className="badge-text">Elite reviewer</span>
      </div>
    </div>
  );
};

export default FeedbackBadge;
