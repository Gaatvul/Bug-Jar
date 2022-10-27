package com.gaatvul.bugtracker.Rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gaatvul.bugtracker.Entities.CommentEntity;

public class CommentRowMapper implements RowMapper<CommentEntity> {

    @Override
    @Nullable
    public CommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        CommentEntity comment = new CommentEntity();

        comment.setCommentText(rs.getString("comment"));
        comment.setCommenter_name(rs.getString("account"));
        comment.setCreatedOn(rs.getObject("date_created", Timestamp.class));

        return comment;
    }

}
