package study.study.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.study.answer.dto.AnswerPatchDto;
import study.study.answer.dto.AnswerPostDto;
import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.Answer;
import study.study.answer.service.AnswerService;
import study.study.post.dto.*;
import study.study.post.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;


    @PostMapping("/{postId}")
    public ResponseEntity create(@RequestBody AnswerPostDto answerPostDto,@PathVariable Long postId){
        Answer answer = answerService.create(answerPostDto, postId);

        return new ResponseEntity<>(new SingleResponseDto<>(AnswerResponseDto.responseCreate(answer)), HttpStatus.CREATED);
    }

    @PatchMapping("/{answerId}")
    public ResponseEntity update (@PathVariable Long answerId, @RequestBody AnswerPatchDto answerPatchDto){

        Answer answer = answerService.update(answerPatchDto, answerId);
        return new ResponseEntity<>(new SingleResponseDto<>(AnswerResponseDto.responseCreate(answer)),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getPosts(@RequestParam int page, @RequestParam int size){
        Page<Answer> answers = answerService.findAnswers(page - 1, size);
        List<AnswerResponseDto> content = getCollect(answers);
        return new ResponseEntity(new MultiResponseDto<>(content,answers),HttpStatus.OK);
    }

    private static List<AnswerResponseDto> getCollect(Page<Answer> answers) {
        return answers.getContent().stream()
                .map(answer -> AnswerResponseDto.builder()
                        .answerId(answer.getAnswerId())
                        .content(answer.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("{answerId}")
    public ResponseEntity getPost(@PathVariable Long answerId){
        Answer answers = answerService.findAnswers(answerId);

        return new ResponseEntity<>(new SingleResponseDto<>(AnswerResponseDto.responseCreate(answers)),HttpStatus.OK);
    }

    @DeleteMapping("{answerId}")
    public ResponseEntity deletePost(@PathVariable Long answerId){
        answerService.delete(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
