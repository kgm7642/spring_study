package org.scoula.service;

import org.scoula.domain.BoardVO;
import org.scoula.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardmapper) {
        this.boardMapper = boardmapper;
    }

    @Override
    public List<BoardVO> getAllBoardByMapper() {
        List<BoardVO> boards = boardMapper.selectAllByMapper();
        return boards;
    }

    @Override
    public List<BoardVO> getAllBoardByAnnotation() {
        List<BoardVO> boards = boardMapper.selectAllByAnnotation();
        return boards;
    }
}
