package com.qh.tangshianalyze.analyze.service.impl;

import com.qh.tangshianalyze.analyze.dao.AnalyzeDao;
import com.qh.tangshianalyze.analyze.entity.PoetryInfo;
import com.qh.tangshianalyze.analyze.model.AuthorCount;
import com.qh.tangshianalyze.analyze.model.WordCount;
import com.qh.tangshianalyze.analyze.service.AnalyzeService;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.sql.Connection;
import java.util.*;

/**
 * Author:qh
 * Created:2019/8/6
 */

public class AnalyzeServiceImpl implements AnalyzeService {

    private final AnalyzeDao analyzeDao;

    public AnalyzeServiceImpl(AnalyzeDao analyzeDao) {
        this.analyzeDao = analyzeDao;
    }

    @Override
    public List<AuthorCount> analyzeAuthorCount() {
        //此处结果并未排序
        //排序方式
        //1.DAO层SQL排序
        //2.Service层进行数据排序
        List<AuthorCount> authorCounts = analyzeDao.analyzeAuthorCount();

        //此处是升序，按照count升序
        authorCounts.sort(new Comparator<AuthorCount>() {
            @Override
            public int compare(AuthorCount o1, AuthorCount o2) {
                //升序*-1  降序
                return o1.getCount().compareTo(o2.getCount());
            }
        });

        return authorCounts;
    }

    @Override
    public List<WordCount> analyzeWordCloud() {
        //1. 查询出所有数据
        //2. 取出title content
        //3. 分词 过滤/w null 空 len<2
        //4. 统计k-v  k是词，v是词频

        Map<String,Integer> map = new HashMap<>();
        List<PoetryInfo> poetryInfos = analyzeDao.queryAllPoetryInfo();

        for(PoetryInfo poetryInfo:poetryInfos){
            List<Term> terms = new ArrayList<>();
            String title = poetryInfo.getTitle();
            String content = poetryInfo.getContent();

            NlpAnalysis.parse(title).getTerms();

            terms.addAll(NlpAnalysis.parse(title).getTerms());
            terms.addAll(NlpAnalysis.parse(content).getTerms());

            Iterator<Term> iterator = terms.iterator();
            while (iterator.hasNext()){
                Term term =iterator.next();
                //词性的过滤
                if (term.getNatureStr()==null||term.getNatureStr().equals("w")){
                    iterator.remove();
                    continue;
                }
                //词的过滤
                if(term.getRealName().length()<2){
                    iterator.remove();
                    continue;
                }

                //统计
                String realName = term.getRealName();
                Integer count = 0;
                if(map.containsKey(realName)){
                    count = map.get(realName)+1;
                }else {
                    count = 1;
                }
                map.put(realName,count);
            }
        }
        List<WordCount> wordCounts = new ArrayList<>();
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            WordCount wordCount = new WordCount();
            wordCount.setCount(entry.getValue());
            wordCount.setWord(entry.getKey());
            wordCounts.add(wordCount);
        }
        return wordCounts;
    }


}
